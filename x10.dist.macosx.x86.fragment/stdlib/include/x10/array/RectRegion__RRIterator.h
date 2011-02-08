#ifndef __X10_ARRAY_RECTREGION__RRITERATOR_H
#define __X10_ARRAY_RECTREGION__RRITERATOR_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERATOR_H_NODEPS
#include <x10/lang/Iterator.h>
#undef X10_LANG_ITERATOR_H_NODEPS
namespace x10 { namespace array { 
class Point;
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
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
class RectRegion;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
namespace x10 { namespace array { 

class RectRegion__RRIterator : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(myRank);
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterator<x10aux::ref<x10::array::Point> >::itable<x10::array::RectRegion__RRIterator > _itable_0;
    
    static x10::lang::Any::itable<x10::array::RectRegion__RRIterator > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > FMGL(min);
    
    x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > FMGL(max);
    
    x10_boolean FMGL(done);
    
    x10aux::ref<x10::lang::Rail<x10_int > > FMGL(cur);
    
    void _constructor(x10aux::ref<x10::array::RectRegion> rr);
    
    static x10aux::ref<x10::array::RectRegion__RRIterator> _make(x10aux::ref<x10::array::RectRegion> rr);
    
    virtual x10_boolean hasNext();
    virtual x10aux::ref<x10::array::Point> next();
    x10_int myRank();
    virtual x10aux::ref<x10::array::RectRegion__RRIterator> x10__array__RectRegion__RRIterator____x10__array__RectRegion__RRIterator__this(
      );
    void __fieldInitializers1705();
    
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
#endif // X10_ARRAY_RECTREGION__RRITERATOR_H

namespace x10 { namespace array { 
class RectRegion__RRIterator;
} } 

#ifndef X10_ARRAY_RECTREGION__RRITERATOR_H_NODEPS
#define X10_ARRAY_RECTREGION__RRITERATOR_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Point.h>
#include <x10/lang/Int.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Rail.h>
#include <x10/array/RectRegion.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_3.h>
#include <x10/lang/Fun_0_3.h>
#include <x10/lang/Fun_0_3.h>
#ifndef X10_ARRAY_RECTREGION__RRITERATOR_H_GENERICS
#define X10_ARRAY_RECTREGION__RRITERATOR_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::RectRegion__RRIterator::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::RectRegion__RRIterator> this_ = new (memset(x10aux::alloc<x10::array::RectRegion__RRIterator>(), 0, sizeof(x10::array::RectRegion__RRIterator))) x10::array::RectRegion__RRIterator();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_RECTREGION__RRITERATOR_H_GENERICS
#endif // __X10_ARRAY_RECTREGION__RRITERATOR_H_NODEPS
