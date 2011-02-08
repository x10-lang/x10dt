#ifndef __X10_ARRAY_POLYMATBUILDER_H
#define __X10_ARRAY_POLYMATBUILDER_H

#include <x10rt.h>


#define X10_ARRAY_MATBUILDER_H_NODEPS
#include <x10/array/MatBuilder.h>
#undef X10_ARRAY_MATBUILDER_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
class PolyMat;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace array { 
class Row;
} } 
namespace x10 { namespace array { 
class PolyRow;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
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
namespace x10 { namespace array { 

class PolyMatBuilder : public x10::array::MatBuilder   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(rank);
    
    void _instance_init();
    
    void _constructor(x10_int rank);
    
    static x10aux::ref<x10::array::PolyMatBuilder> _make(x10_int rank);
    
    virtual x10aux::ref<x10::array::PolyMat> toSortedPolyMat(x10_boolean isSimplified);
    static x10_int FMGL(ZERO);
    
    static inline x10_int FMGL(ZERO__get)() {
        return x10::array::PolyMatBuilder::FMGL(ZERO);
    }
    static x10_int FMGL(GE);
    
    static inline x10_int FMGL(GE__get)() {
        return x10::array::PolyMatBuilder::FMGL(GE);
    }
    static x10_int FMGL(LE);
    
    static inline x10_int FMGL(LE__get)() {
        return x10::array::PolyMatBuilder::FMGL(LE);
    }
    static x10_int X(x10_int axis);
    virtual void add(x10_int coeff, x10_int op, x10_int k);
    x10_int rank();
    virtual x10aux::ref<x10::array::PolyMatBuilder> x10__array__PolyMatBuilder____x10__array__PolyMatBuilder__this(
      );
    public: virtual void add(x10aux::ref<x10::array::Row> p0);
    public: virtual void add(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > p0);
    
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
#endif // X10_ARRAY_POLYMATBUILDER_H

namespace x10 { namespace array { 
class PolyMatBuilder;
} } 

#ifndef X10_ARRAY_POLYMATBUILDER_H_NODEPS
#define X10_ARRAY_POLYMATBUILDER_H_NODEPS
#include <x10/array/MatBuilder.h>
#include <x10/lang/Int.h>
#include <x10/array/PolyMat.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/array/Row.h>
#include <x10/array/PolyRow.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_1.h>
#ifndef X10_ARRAY_POLYMATBUILDER_H_GENERICS
#define X10_ARRAY_POLYMATBUILDER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::PolyMatBuilder::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::PolyMatBuilder> this_ = new (memset(x10aux::alloc<x10::array::PolyMatBuilder>(), 0, sizeof(x10::array::PolyMatBuilder))) x10::array::PolyMatBuilder();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_POLYMATBUILDER_H_GENERICS
#endif // __X10_ARRAY_POLYMATBUILDER_H_NODEPS
