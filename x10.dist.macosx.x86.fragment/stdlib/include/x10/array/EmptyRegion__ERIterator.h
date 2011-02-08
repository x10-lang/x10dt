#ifndef __X10_ARRAY_EMPTYREGION__ERITERATOR_H
#define __X10_ARRAY_EMPTYREGION__ERITERATOR_H

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
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace util { 
class NoSuchElementException;
} } 
namespace x10 { namespace array { 

class EmptyRegion__ERIterator : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(myRank);
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterator<x10aux::ref<x10::array::Point> >::itable<x10::array::EmptyRegion__ERIterator > _itable_0;
    
    static x10::lang::Any::itable<x10::array::EmptyRegion__ERIterator > _itable_1;
    
    void _instance_init();
    
    void _constructor(x10_int r);
    
    static x10aux::ref<x10::array::EmptyRegion__ERIterator> _make(x10_int r);
    
    virtual x10_boolean hasNext();
    virtual x10aux::ref<x10::array::Point> next();
    x10_int myRank();
    virtual x10aux::ref<x10::array::EmptyRegion__ERIterator> x10__array__EmptyRegion__ERIterator____x10__array__EmptyRegion__ERIterator__this(
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
#endif // X10_ARRAY_EMPTYREGION__ERITERATOR_H

namespace x10 { namespace array { 
class EmptyRegion__ERIterator;
} } 

#ifndef X10_ARRAY_EMPTYREGION__ERITERATOR_H_NODEPS
#define X10_ARRAY_EMPTYREGION__ERITERATOR_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Point.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#include <x10/util/NoSuchElementException.h>
#ifndef X10_ARRAY_EMPTYREGION__ERITERATOR_H_GENERICS
#define X10_ARRAY_EMPTYREGION__ERITERATOR_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::EmptyRegion__ERIterator::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::EmptyRegion__ERIterator> this_ = new (memset(x10aux::alloc<x10::array::EmptyRegion__ERIterator>(), 0, sizeof(x10::array::EmptyRegion__ERIterator))) x10::array::EmptyRegion__ERIterator();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_EMPTYREGION__ERITERATOR_H_GENERICS
#endif // __X10_ARRAY_EMPTYREGION__ERITERATOR_H_NODEPS
