#ifndef __X10_ARRAY_POLYMAT_H
#define __X10_ARRAY_POLYMAT_H

#include <x10rt.h>


#define X10_ARRAY_MAT_H_NODEPS
#include <x10/array/Mat.h>
#undef X10_ARRAY_MAT_H_NODEPS
namespace x10 { namespace array { 
class PolyRow;
} } 
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
class PolyMatBuilder;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace array { 
class UnboundedRegionException;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 

class PolyMat : public x10::array::Mat<x10aux::ref<x10::array::PolyRow> >
  {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(rank);
    
    static x10aux::itable_entry _itables[4];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Fun_0_1<x10_int, x10aux::ref<x10::array::PolyRow> >::itable<x10::array::PolyMat > _itable_0;
    
    static x10::lang::Any::itable<x10::array::PolyMat > _itable_1;
    
    static x10::lang::Iterable<x10aux::ref<x10::array::PolyRow> >::itable<x10::array::PolyMat > _itable_2;
    
    void _instance_init();
    
    x10_boolean FMGL(isSimplified);
    
    void _constructor(x10_int rows, x10_int cols, x10aux::ref<x10::lang::Fun_0_2<x10_int, x10_int, x10_int> > init,
                      x10_boolean isSimplified);
    
    static x10aux::ref<x10::array::PolyMat> _make(x10_int rows, x10_int cols,
                                                  x10aux::ref<x10::lang::Fun_0_2<x10_int, x10_int, x10_int> > init,
                                                  x10_boolean isSimplified);
    
    virtual x10aux::ref<x10::array::PolyMat> simplifyParallel(
      );
    virtual x10aux::ref<x10::array::PolyMat> simplifyAll(
      );
    virtual x10aux::ref<x10::array::PolyMat> eliminate(x10_int k,
                                                       x10_boolean simplifyDegenerate);
    virtual x10_boolean isRect();
    virtual x10_int rectMin(x10_int axis);
    virtual x10_int rectMax(x10_int axis);
    virtual x10aux::ref<x10::lang::Rail<x10_int > > rectMin(
      );
    virtual x10aux::ref<x10::lang::Rail<x10_int > > rectMax(
      );
    virtual x10_boolean isZeroBased();
    virtual x10_boolean isBounded();
    virtual x10_boolean isEmpty();
    virtual x10aux::ref<x10::array::PolyMat> __or(x10aux::ref<x10::array::PolyMat> that);
    virtual x10aux::ref<x10::lang::String> toString();
    x10_int rank();
    virtual x10aux::ref<x10::array::PolyMat> x10__array__PolyMat____x10__array__PolyMat__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_POLYMAT_H

namespace x10 { namespace array { 
class PolyMat;
} } 

#ifndef X10_ARRAY_POLYMAT_H_NODEPS
#define X10_ARRAY_POLYMAT_H_NODEPS
#include <x10/array/Mat.h>
#include <x10/array/PolyRow.h>
#include <x10/lang/Int.h>
#include <x10/array/PolyMatBuilder.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/String.h>
#include <x10/array/UnboundedRegionException.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#ifndef X10_ARRAY_POLYMAT_H_GENERICS
#define X10_ARRAY_POLYMAT_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::PolyMat::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::PolyMat> this_ = new (memset(x10aux::alloc<x10::array::PolyMat>(), 0, sizeof(x10::array::PolyMat))) x10::array::PolyMat();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_POLYMAT_H_GENERICS
#endif // __X10_ARRAY_POLYMAT_H_NODEPS
